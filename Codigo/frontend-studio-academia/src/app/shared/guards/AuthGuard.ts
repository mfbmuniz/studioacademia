
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot,} from "@angular/router";

import {Observable} from "rxjs";
import {AuthService} from "../../services/AuthService";
import {Injectable} from "@angular/core";
import {UserRoleService} from "../../services/UserRoleService";

@Injectable({
  providedIn: "root",
})
export class AuthGuard implements CanActivate {
  constructor(
    public router: Router,
    private authService: AuthService,
    private userRoleService: UserRoleService
  ) {

  }

  private checked: boolean = false;
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean | Observable<boolean> {
    if (!this.authService.isAuth()) {
      this.authService.logout();
      this.router.navigate(["/home"]);
      return false;
    } else {
      console.log(this.authService.getSession())
      console.log("-> ",this.checked)
        this.validateRoutesByRole(this.authService.getSession().roles)
    }

    return true;
  }

  private validateRoutesByRole(roles: any) {
    if (roles.includes('ADMIN') && !this.checked){
      this.router.navigate(["/adm"]);
    }else if (roles.includes("ALUNO") && !this.checked){
      this.router.navigate(["/aluno"]);
    }else if (!this.checked){
        if (this.router.url !== "/home")
          console.error("User type not allowed");

    }
    this.checked = true;
  }
}
