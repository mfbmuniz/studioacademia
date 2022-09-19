import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {AuthService} from "./AuthService";

@Injectable({
    providedIn: "root",
})
export class UserRoleService {
    roles: any;

    constructor(
        private router: Router,
        private authService: AuthService
    ) {
        this.setUserRoles();
    }

    setUserRoles() {
        this.authService.getObservableSession().subscribe((session) => {
            if (session) {
                this.roles = session.roles;
            } else {
                this.roles = null;
            }
        });
    }

    isAdmin() {
        return this.roles ? this.roles.includes("ADMIN") : false;
    }

}
