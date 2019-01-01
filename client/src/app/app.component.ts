import {Component, OnInit} from '@angular/core';
import { ApiService } from './api-service.service';
import { PersistentStorageService } from './persistent-storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  // puts the navbar only if you are logged in.

  isAdmin = false;
  title = 'client';

  constructor(private api: ApiService, private storage: PersistentStorageService, private router: Router) {

    // this.api.loginStatus.subscribe(res => {
    //   console.log(res);
    //   if (res !== null) {
    //     if (res) {
    //       this.isLogin = true;
    //     }
    //   }
    // });
  }

  ngOnInit() {
    this.router.events.subscribe((url: any) => {
      if (url.url === '/admin') {
        this.isAdmin = true;
      }
    });
  }

  // async ngOnInit() {
  //   try {
  //     // Retrieve username and password from local storage in the browser.
  //     const username = await this.storage.getValue('username');
  //     const password = await this.storage.getValue('password');
  //
  //     if (username && password) {
  //       await this.login(username, password);
  //     }
  //   } catch (e) {
  //     console.log(e);
  //   }
  // }
  //
  // async login(username: string, password: string) {
  //   try {
  //     const result = await this.api.doLogin(username, password);
  //
  //     if (result) {
  //       this.isLogin = true;
  //     }
  //   } catch (err) {
  //     console.log(err);
  //   }
  // }
}
