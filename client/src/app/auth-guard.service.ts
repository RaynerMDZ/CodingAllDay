import { Injectable } from '@angular/core';
import {CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router} from '@angular/router';
import { ApiService } from './api-service.service';
import { PersistentStorageService } from './persistent-storage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {

  constructor(private api: ApiService, private router: Router, private persistence: PersistentStorageService) {}

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    return new Promise<boolean>(resolve => {
      let username = null;
      let password = null;
      this.persistence.getValue('username').then(user => {
        username = user;
      }).then(() => {
        this.persistence.getValue('password').then(pass => {
          password = pass;
        }).then(() => {
          if (username && password) {
            this.api.doLogin(username, password).then(response => {
              if (response.message === true) {
                this.api.loginStatus.next(true);
                resolve(true);
              }
              resolve(false);
            });
          };
        });
      });
    });

  }

}
