import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ProfileComponent} from './profile/profile.component';
import {SettingsComponent} from './settings/settings.component';
import {HomeComponent} from './home/home.component';
import {AboutMeComponent} from './about-me/about-me.component';
import {ContactMeComponent} from './contact-me/contact-me.component';
import {PostDetailsComponent} from './post-details/post-details.component';
import {CreatePostComponent} from './create-post/create-post.component';
import {LoginComponent} from './login/login.component';
import {AuthGuard} from './auth-guard.service';

const routes: Routes = [

  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'profile',
    component: ProfileComponent
  },
  {
    path: 'settings',
    component: SettingsComponent
  },
  {
    path: 'aboutMe',
    component: AboutMeComponent
  },
  {
    path: 'contactMe',
    component: ContactMeComponent
  },
  {
    path: 'post/:id',
    component: PostDetailsComponent,
    pathMatch: 'full'
  },
  {
    path: 'createPost',
    component: CreatePostComponent,
    canActivate: [AuthGuard],
  },
  {
    path: 'login',
    component: LoginComponent
  },
  { path: '**', redirectTo: '', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

