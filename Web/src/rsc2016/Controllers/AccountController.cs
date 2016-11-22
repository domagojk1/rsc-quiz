using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using rsc2016.Helpers.Models;
using rsc2016.Models;
using rsc2016.Services.Interfaces;
using rsc2016.ViewModels;

namespace rsc2016.Controllers
{
    public class AccountController : Controller
    {
        private readonly IMembershipService _membershipService;
        private readonly SignInManager<User> _signInManager;
        private UserManager<User> _userManager;

        public AccountController(UserManager<User> userManager, SignInManager<User> signInManager,
            IMembershipService membershipService)
        {
            _userManager = userManager;
            _signInManager = signInManager;
            _membershipService = membershipService;
        }


        [HttpGet]
        public IActionResult Register()
        {
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Register(RegisterViewModel model)
        {
            var result = await _membershipService.Register(model);
            if (result.Status)
                return RedirectToAction("Index", "Home");

            CreateModelStateError(result);

            return View();
        }


        [HttpGet]
        public IActionResult Login(string returnUrl = "")
        {
            var model = new LoginViewModel {ReturnUrl = returnUrl};
            return View(model);
        }

        [HttpPost]
        public async Task<IActionResult> Login(LoginViewModel model)
        {
            var result = await _membershipService.Login(model);

            if (result.Status)
                if (!string.IsNullOrEmpty(model.ReturnUrl) && Url.IsLocalUrl(model.ReturnUrl))
                    return Redirect(model.ReturnUrl);
                else
                    return RedirectToAction("Index", "Home");
            CreateModelStateError(result);
            return View(model);
        }

        [HttpPost]
        public async Task<IActionResult> Logout()
        {
            await _signInManager.SignOutAsync();

            return RedirectToAction("Index", "Home");
        }

        private void CreateModelStateError(Result result)
        {
            if (result.Errors.Count != 0)
                foreach (var error in result.Errors)
                    ModelState.AddModelError(error.Field, error.Message);
            else
                ModelState.AddModelError("", result.Message);
        }
    }
}