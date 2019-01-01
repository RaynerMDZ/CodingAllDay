import { Component, OnInit } from '@angular/core';
import {ApiService} from '../api-service.service';
import {PersistentStorageService} from '../persistent-storage.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})

export class NavbarComponent implements OnInit {

  public isLoggedIn = false;
  webTitle = 'Coding All Day';

  constructor(private api: ApiService, private persistence: PersistentStorageService, private router: Router) {
    this.api.loginStatus.subscribe((res) => {
      if (res !== null) {
        this.isLoggedIn = res;
      }
    });
  }

  async ngOnInit() {
    try {
      const username = await this.persistence.getValue('username');
      const password = await this.persistence.getValue('password');

      if (username && password ) {
        const response = await this.api.doLogin(username, password);

        if (response.message === true) {
          this.api.loginStatus.next(true);
          this.isLoggedIn = true;
        }
      }

    } catch (e) {
      console.log(e);
    }
  }

  private async logout() {
    try {
      const res = await this.api.doLogout();

      if (res) {
        this.isLoggedIn = false;
        await this.router.navigate(['']);
      }
    } catch (e) {
      console.log(e);
    }

  }

}
