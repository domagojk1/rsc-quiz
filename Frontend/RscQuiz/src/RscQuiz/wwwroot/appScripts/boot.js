"use strict";
///<reference path="./../typings/globals/core-js/index.d.ts"/>
var platform_browser_dynamic_1 = require('@angular/platform-browser-dynamic');
var app_component_1 = require('./components/app.component');
var http_1 = require('@angular/http');
var app_routes_1 = require('./app.routes');
var common_1 = require('@angular/common');
var core_1 = require('@angular/core');
var common_2 = require('@angular/common');
platform_browser_dynamic_1.bootstrap(app_component_1.AppComponent, [
    http_1.HTTP_PROVIDERS,
    app_routes_1.appRouterProviders,
    core_1.provide(common_1.LocationStrategy, { useClass: common_1.HashLocationStrategy }),
    core_1.provide(common_2.APP_BASE_HREF, { useValue: '/' })
]);
//# sourceMappingURL=boot.js.map