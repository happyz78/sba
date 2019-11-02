import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable()
export class JwtInterceptor implements HttpInterceptor {

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        const token = localStorage.getItem('access_token');
        console.log('in the jwtInterceptor:' + token);
        if (token) {
            request = request.clone({
                setHeaders: {
                     Authorization: `${token}`
                }
            });
        }

        return next.handle(request);
    }
}
