import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import {PersistentStorageService} from './persistent-storage.service';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiEndpoint = 'http://localhost:8080';
  public loginStatus: BehaviorSubject<boolean> = new BehaviorSubject(null);

  constructor(private http: HttpClient, private persistence: PersistentStorageService) { }

  public async deleteComment(id: number): Promise<any> {

    try {

      const username = await this.persistence.getValue('username');
      const password = await this.persistence.getValue('password');

      const obj = {
        username: username,
        password: password,
        id: id
      };

      return this.http.post<any>(`${this.apiEndpoint}/comments/delete-comment`, obj).toPromise();

    } catch (e) {
      console.log(e);
    }

  }

  public async deletePost(id: number): Promise<any> {

    try {

      const username = await this.persistence.getValue('username');
      const password = await this.persistence.getValue('password');

      const obj = {
        username: username,
        password: password,
        postId: id
      };

      return this.http.post<any>(`${this.apiEndpoint}/posts/delete-post`, obj).toPromise();

    } catch (e) {
      console.log(e);
    }

  }

  public createComment(id: number, name: string, body: string): Promise<any> {

    const obj = {
      id: id,
      name: name,
      body: body
    };

    return this.http.post<any>(`${this.apiEndpoint}/comments/create-comment`, obj).toPromise();
  }

  public async createPost(title: string, body: string, featurePhoto: string): Promise<any> {

    try {

      const username = await this.persistence.getValue('username');
      const password = await this.persistence.getValue('password');

      const obj = {
        username: username,
        password: password,
        title: title,
        body: body,
        featurePhoto: featurePhoto || null
      };

      return this.http.post<any>(`${this.apiEndpoint}/posts/create-post`, obj).toPromise();

    } catch (e) {
      console.log(e);
    }
  }

  public getPost(id: number): Promise<Post> {
    return this.http.get<Post>(`${this.apiEndpoint}/posts/get-post/${id}`).toPromise();
  }

  public getPosts(limit: number = 10, page: number = 1): Promise<Array<Post>> {
    return this.http.get<Array<Post>>(`${this.apiEndpoint}/posts/get-posts?limit=${limit}&page=${page}`).toPromise();
  }

  public doLogin(username: string, password: string): Promise<any> {

    const credentials = {
      username: username,
      password: password
    };

    return this.http.post(`${this.apiEndpoint}/users/login`, credentials).toPromise();
  }

  public doLogout(): Promise<boolean> {
    return new Promise<boolean>((resolve) => {
      this.persistence.deleteValue('username');
      this.persistence.deleteValue('password');
      this.loginStatus.next(false);
      resolve(true);
    });
  }

  public getProfile(id: number) {
    return this.http.get<Profile>(`${this.apiEndpoint}/profile/get-profile/${id}`).toPromise();
  }
}

export interface Post {
  id: number;
  title: string;
  body: string;
  date: Date;
  comments: Comment[];
  featurePhoto: string;
};

export interface Comment {
  id: number;
  name: string;
  body: string;
  date: Date;
};

export interface Profile {
  fistName: string;
  lastName: string;
  avatarImg: string;
  bio: string;
  dateOfBirth: string;
  country: string;
};
