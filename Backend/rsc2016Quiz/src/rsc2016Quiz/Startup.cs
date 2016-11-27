using System;
using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Cors.Infrastructure;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.AspNetCore.SignalR.Infrastructure;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.IdentityModel.Tokens;
using rsc2016Quiz.Authentication;
using rsc2016Quiz.Models;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using rsc2016Quiz.Dtos;
using rsc2016Quiz.Dtos.Authentication;
using rsc2016Quiz.Helpers;
using rsc2016Quiz.Helpers.ResultModels;
using rsc2016Quiz.Middleware;
using rsc2016Quiz.Repository;
using rsc2016Quiz.Services;
using rsc2016Quiz.Services.Interfaces;

namespace rsc2016Quiz
{
    public class Startup
    {

        private RsaSecurityKey key;
        private TokenAuthOptions tokenOptions;
        public static IConnectionManager ConnectionManager;

        public Startup(IHostingEnvironment env)
        {
            var builder = new ConfigurationBuilder()
                .SetBasePath(env.ContentRootPath)
                .AddJsonFile("appsettings.json", optional: true, reloadOnChange: true)
                .AddJsonFile($"appsettings.{env.EnvironmentName}.json", optional: true);

            if (env.IsEnvironment("Development"))
            {
                // This will push telemetry data through Application Insights pipeline faster, allowing you to view results immediately.
                builder.AddApplicationInsightsSettings(developerMode: true);
            }

            builder.AddEnvironmentVariables();
            Configuration = builder.Build();
        }

        public IConfigurationRoot Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container
        public void ConfigureServices(IServiceCollection services)
        {

            #region Token Config
            // *** CHANGE THIS FOR PRODUCTION USE ***
            // Here, we're generating a random key to sign tokens - obviously this means
            // that each time the app is started the key will change, and multiple servers 
            // all have different keys. This should be changed to load a key from a file 
            // securely delivered to your application, controlled by configuration.
            //
            // See the RSAKeyUtils.GetKeyParameters method for an examle of loading from
            // a JSON file.
            var keyParams = RSAKeyUtils.GetRandomKey(); //TODO secure storage

            // Create the key, and a set of token options to record signing credentials 
            // using that key, along with the other parameters we will need in the 
            // token controlller.
            key = new RsaSecurityKey(keyParams);
            tokenOptions = new TokenAuthOptions
            {
                Audience = Configuration["TokenAudience"],
                Issuer = Configuration["TokenIssuser"],
                SigningCredentials = new SigningCredentials(key, SecurityAlgorithms.RsaSha256Signature)
            };

            // Save the token options into an instance so they're accessible to the 
            // controller.
            services.AddSingleton(tokenOptions);

            // Enable the use of an [Authorize("Bearer")] attribute on methods and classes to protect.
            services.AddAuthorization(auth =>
            {
                auth.AddPolicy("Bearer", new AuthorizationPolicyBuilder()
                    //     .AddAuthenticationSchemes(JwtBearerDefaults.AuthenticationScheme‌​)
                    .RequireAuthenticatedUser().Build());
            });

            //   services.Configure<Settings>(Configuration.GetSection("App"));

            #endregion

            services.AddSignalR(options =>
            {
                options.Hubs.EnableDetailedErrors = true;
            });

            #region Services

            services.AddScoped<IOAuthHandler, OAuthHandler>();
            services.AddScoped<IMembershipService, MembershipService>();
            services.AddScoped<IUserRepository, UserRepository>();
            services.AddScoped<IEncryptionService, EncryptionService>();
            services.AddScoped<IApiErrorHandler, ApiErrorHandler>();
            services.AddScoped<IEventRepository, EventRepository>();
            services.AddScoped<ITeamRepository, TeamRepository>();
            services.AddTransient<IConnectionManager, ConnectionManager>();
            

            #endregion

            services.AddIdentity<User, IdentityRole>()
                .AddEntityFrameworkStores<RscContext>();

            services.AddDbContext<RscContext>(options =>
                    options.UseSqlServer(Configuration.GetConnectionString("DefaultConnection")));

            services.AddSwaggerGen();


            var corsBuilder = new CorsPolicyBuilder();
            corsBuilder.AllowAnyHeader();
            corsBuilder.AllowAnyMethod();
            corsBuilder.AllowAnyOrigin();
            corsBuilder.AllowCredentials();
            services.AddCors(options => { options.AddPolicy("AllowAll", corsBuilder.Build()); });

            // Add framework services.
            services.AddApplicationInsightsTelemetry(Configuration);

            services.AddSignalR();

            services.AddMvc()
               .AddJsonOptions(opt =>
               {
                   opt.SerializerSettings.ContractResolver = new CamelCasePropertyNamesContractResolver();
                   opt.SerializerSettings.PreserveReferencesHandling = PreserveReferencesHandling.None;
               }); services.AddMvc();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, ILoggerFactory loggerFactory, IServiceProvider serviceProvider)
        {
            ConnectionManager = serviceProvider.GetService<IConnectionManager>();

            loggerFactory.AddConsole(Configuration.GetSection("Logging"));
            loggerFactory.AddDebug();

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
           //     app.UseBrowserLink();
            }

            app.UseFacebookAuthentication(new FacebookOptions()
            {
                AppId = "552017744992396",
                AppSecret = "71738ff00bc9ff5932bed560ddbd9c10",
                Scope = { "email" }
            });


            #region Tokens

            var tokenValidationParametars = new TokenValidationParameters
            {
                IssuerSigningKey = key,
                ValidAudience = tokenOptions.Audience,
                ValidIssuer = tokenOptions.Issuer,
                ValidateLifetime = true,
                ClockSkew = TimeSpan.FromMinutes(0)
            };

            var options = new JwtBearerOptions
            {
                TokenValidationParameters = tokenValidationParametars
            };

            app.UseJwtBearerAuthentication(options);

            #endregion

            app.UseStaticFiles();

            app.UseCors("AllowAll");

            app.UseApplicationInsightsRequestTelemetry();

            app.UseApplicationInsightsExceptionTelemetry();

            // Enable middleware to serve generated Swagger as a JSON endpoint
            app.UseSwagger();

            // Enable middleware to serve swagger-ui assets (HTML, JS, CSS etc.)
            app.UseSwaggerUi();

            Mapper.Initialize(config =>
            {
                config.CreateMap<TokenDto, UserTokenResult>().ReverseMap();
                config.CreateMap<ErrorMessageDto, UserTokenResult>().ReverseMap();
                config.CreateMap<ErrorMessageDto, Result>().ReverseMap();
                config.CreateMap<ErrorMessageDto, ErrorList>().ReverseMap();
                config.CreateMap<FilePathDto, FileResult>().ReverseMap();
                config.CreateMap<EventDto, Event>().ReverseMap();
            });

            app.UseMiddleware(typeof(ErrorHandlingMiddleware));
            app.UseApplicationInsightsRequestTelemetry();

            app.UseApplicationInsightsExceptionTelemetry();

            app.UseMvc();

            app.UseWebSockets();
            app.UseSignalR();
        }
    }
}
