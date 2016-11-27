///<reference path="./../typings/globals/core-js/index.d.ts"/>
import {bootstrap}    from '@angular/platform-browser-dynamic';
import {AppComponent} from './components/app.component';
import { HTTP_PROVIDERS } from '@angular/http';
import { appRouterProviders } from './app.routes';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import {provide} from '@angular/core';
import {APP_BASE_HREF} from '@angular/common';

bootstrap(AppComponent, [
    HTTP_PROVIDERS,
    appRouterProviders,
    provide(LocationStrategy, { useClass: HashLocationStrategy }),
    provide(APP_BASE_HREF, { useValue: '/' })
]);