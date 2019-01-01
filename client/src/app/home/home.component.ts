import { Component, OnInit } from '@angular/core';
import { ApiService, Post } from '../api-service.service';
import { UtilsService } from '../utils.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  public isLoggedIn = false;

  private posts: Array<Post> = null;

  constructor(private api: ApiService, public utils: UtilsService) {
    this.api.loginStatus.subscribe((res) => {
      if (res !== null) {
        this.isLoggedIn = res;
      }
    });
  }

  async ngOnInit() {
    await this.getPosts();
  }

  private async getPosts() {
    try {
      this.posts = await this.api.getPosts(100, 1);
      console.log(this.posts);
    } catch (e) {
      console.log(e);
    }
  }

  private async deletePost(id: number) {
    try {
      const res = await this.api.deletePost(id);

      if (res.status === 200) {
        await this.getPosts();
      }
    } catch (e) {
      console.log(e);
    }
  }
}
