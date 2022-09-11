
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot,} from "@angular/router";

import {Observable} from "rxjs";
import {AuthService} from "../../services/AuthService";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: "root",
})
export class AuthGuard implements CanActivate {
  constructor(
    public router: Router,
    private authService: AuthService,

  ) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | Observable<boolean> {
    if (!this.authService.isAuth()) {
      console.log("nops")
      this.authService.logout();
      this.router.navigate(["/login"]);
      return false;
    } else {
        this.validateRoutes(this.authService.getSession().role)
    }

    return true;
  }

  validateRoutes( state: any ) {
   console.log(state)
    // if (this.userRoleService.isRegManager()) {
    //   if (
    //     state.url.includes("/gamefic") ||
    //     (state.url.includes("/app/") &&
    //       (state.url.includes("/prospects") ||
    //         state.url.includes("/propostas")))
    //   )
    //     this.router.navigate(["/ar/agentes"]);
    // }

  }

}
