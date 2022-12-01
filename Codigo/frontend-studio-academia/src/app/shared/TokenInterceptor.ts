import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";

import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {AuthService} from "../services/AuthService";

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private authService: AuthService) {}

  private interceptableApisUrls = [environment.apiUrl];

  isAnInterceptableRequest(requestUrl: string): Boolean {
    for (let apiUrl of this.interceptableApisUrls) {
      if (requestUrl.includes(apiUrl)) return true;
    }
    return false;
  }

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    let session = this.authService.getSession();
    // console.log("testando", session, this.authService.getSession())
    if (
      session &&
      Object.keys(session).length > 0 &&
      this.isAnInterceptableRequest(request?.url)
    ) {

      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${session.token}`,
        },
      });
    }

    return next.handle(request);
  }
}
