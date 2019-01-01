import { Component, OnInit } from '@angular/core';
import {ApiService} from '../api-service.service';
import { PersistentStorageService } from '../persistent-storage.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  public username: string;
  public password: string;

  constructor(private api: ApiService, private persistence: PersistentStorageService, private router: Router) {
  }

  public async doLogin() {

    const login: any = await this.api.doLogin(this.username, this.password);

    if (login.message === true) {
      this.persistence.setValue('username', this.username);
      this.persistence.setValue('password', this.password);

      // Emit a message to the Behavior Subject
      this.api.loginStatus.next(true);

      // Redirect to home page
      await this.router.navigate(['']);

      return;
    }

    // Show error message

  }

  ngOnInit() {
  }

}
