using System;
using System.IO;
using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Cors.Infrastructure;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using Newtonsoft.Json;
using Newtonsoft.Json.Serialization;
using rsc2016.Dtos;
using rsc2016.Helpers;
using rsc2016.Helpers.Models;
using rsc2016.Models;
using rsc2016.Services;
using rsc2016.Services.Interfaces;
using SL.Core.Authentication;

namespace rsc2016
{
    public class Startup
    {
        private RsaSecurityKey key;
        private TokenAuthOptions tokenOptions;

        public Startup(IHostingEnvironment env)
        {
            var builder = new ConfigurationBuilder()
                .SetBasePath(env.ContentRootPath)
                .AddJsonFile("appsettings.json", optional: true, reloadOnChange: true)
                .AddJsonFile($"appsettings.{env.EnvironmentName}.json", optional: true)
                .AddEnvironmentVariables();

            if (env.IsDevelopment())
            {
                // This will push telemetry data through Application Insights pipeline faster, allowing you to view results immediately.
                builder.AddApplicationInsightsSettings(developerMode: true);
            }
            Configuration = builder.Build();
        }

        public IConfigurationRoot Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
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

            #region Services

            services.AddSingleton(provider => Configuration);
            services.AddScoped<IMembershipService, MembershipService>();
            services.AddScoped<IApiErrorHandler, ApiErrorHandler>();

            #endregion


            services.AddIdentity<User, IdentityRole>(o =>
                    {
                        o.Password.RequireDigit = false;
                        o.Password.RequireLowercase = false;
                        o.Password.RequireUppercase = false;
                        o.Password.RequireNonAlphanumeric = false;
                        o.Password.RequiredLength = 5;
                    }
                )
                .AddEntityFrameworkStores<RscContext>()
                .AddDefaultTokenProviders();

            services.AddDbContext<RscContext>(options =>
                    options.UseSqlServer(Configuration.GetConnectionString("DefaultConnection")));

            var corsBuilder = new CorsPolicyBuilder();
            corsBuilder.AllowAnyHeader();
            corsBuilder.AllowAnyMethod();
            corsBuilder.AllowAnyOrigin();
            corsBuilder.AllowCredentials();
            services.AddCors(options => { options.AddPolicy("AllowAll", corsBuilder.Build()); });


            // Add framework services.
            services.AddApplicationInsightsTelemetry(Configuration);

            services.AddMvc()
                .AddJsonOptions(opt =>
                {
                    opt.SerializerSettings.ContractResolver = new CamelCasePropertyNamesContractResolver();
                    opt.SerializerSettings.PreserveReferencesHandling = PreserveReferencesHandling.None;
                });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IHostingEnvironment env, ILoggerFactory loggerFactory)
        {
            loggerFactory.AddConsole(Configuration.GetSection("Logging"));
            loggerFactory.AddDebug();


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

            app.UseApplicationInsightsRequestTelemetry();

            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
                app.UseBrowserLink();
            }
            else
            {
                app.UseExceptionHandler("/Home/Error");
            }

            app.UseIdentity();

            app.UseApplicationInsightsExceptionTelemetry();

            app.UseStaticFiles();

            Mapper.Initialize(config =>
            {
                config.CreateMap<TokenDto, UserTokenResult>().ReverseMap();
                config.CreateMap<ErrorMessageDto, UserTokenResult>().ReverseMap();
                config.CreateMap<ErrorMessageDto, Result>().ReverseMap();
            });

            app.UseMvc(routes =>
            {
                routes.MapRoute(
                    name: "default",
                    template: "{controller=Home}/{action=Index}/{id?}");
            });
        }
    }
}
