import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ApiService} from '../api-service.service';
import {UtilsService} from '../utils.service';

// @ts-ignore
@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.scss']
})
export class CreatePostComponent implements OnInit {

  public title: string;
  public featurePhoto: string;
  public body: string;

  public editorConfig = {
    'editable': true,
    'spellcheck': true,
    'height': '220px',
    'minHeight': '0',
    'width': 'auto',
    'minWidth': '0',
    'enableToolbar': true,
    'showToolbar': true,
    'placeholder': 'Enter text here...',
    'toolbar': [
      ['bold', 'italic', 'underline', 'strikeThrough', 'superscript', 'subscript'],
      ['fontName', 'fontSize', 'color'],
      ['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull', 'indent', 'outdent'],
      ['cut', 'copy', 'delete', 'removeFormat', 'undo', 'redo'],
      ['paragraph', 'blockquote', 'removeBlockquote', 'horizontalLine', 'orderedList', 'unorderedList'],
      ['link', 'unlink', 'image', 'video']
    ]
  };

  constructor(private route: ActivatedRoute, private api: ApiService, public utils: UtilsService, private router: Router) {
  }

  ngOnInit() {
  }

  public async createPost() {

    try {

      const res = await this.api.createPost(this.title, this.body, this.featurePhoto);

      if (res.status === 200) {
       await this.router.navigate(['']);
      }

    } catch (e) {
      console.log(e);
    }

  }

}
