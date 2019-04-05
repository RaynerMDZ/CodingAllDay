import { Component, OnInit } from '@angular/core';
import {ApiService, Profile} from '../api-service.service';

// @ts-ignore
@Component({
  selector: 'app-about-me',
  templateUrl: './about-me.component.html',
  styleUrls: ['./about-me.component.scss']
})
export class AboutMeComponent implements OnInit {

  public profile: Profile;
  id = 1;

  constructor(private api: ApiService) { }

  async ngOnInit() {
    await this.getProfile();
  }

  public async getProfile() {

    try {

      this.profile = await this.api.getProfile(this.id);

    } catch (e) {
      console.log(e);
    }
  }
}
