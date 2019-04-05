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

  constructor(private api: ApiService,
              private storage: PersistentStorageService,
              private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe(
      (url: any) => {
      if (url.url === '/admin') {
        this.isAdmin = true;
      }
    });
  }

}
