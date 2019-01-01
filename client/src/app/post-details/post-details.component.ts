import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {ApiService, Post} from '../api-service.service';
import { UtilsService } from '../utils.service';

// @ts-ignore
@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.scss']
})
export class PostDetailsComponent implements OnInit, OnDestroy {

  public isLoggedIn = false;

  public post: Post;
  id: number;
  private sub: any;
  public name: string;
  public body: string;

  constructor(private route: ActivatedRoute, private api: ApiService, public utils: UtilsService) {
    this.api.loginStatus.subscribe((res) => {
      if (res !== null) {
        this.isLoggedIn = res;
      }
    });
  }

  async ngOnInit() {
    this.sub = this.route.params.subscribe(async (params) => {
      this.id = +params['id']; // (+) converts string 'id' to a number

      // In a real app: dispatch action to load the details here.
      await this.getPostDetails();
    });
  }

  async getPostDetails() {
    try {
      this.post = await this.api.getPost(this.id);
      this.post.comments = this.post.comments.reverse();
    } catch (e) {
      console.log(e);
    }
  }

  async createComment() {
    const name = this.name ? this.name : 'Anonymous';
    await this.api.createComment(this.id, name, this.body);
    await this.getPostDetails();
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  private async deleteComment(id: number) {

    try {

      const res = await this.api.deleteComment(id);

      if (res.status === 200) {
        await this.getPostDetails();
      }

    } catch (e) {
      console.log(e);
    }
  }

}
