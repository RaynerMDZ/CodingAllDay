import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { SettingsComponent } from './settings/settings.component';

// 3rd party
import { MDBBootstrapModule } from 'angular-bootstrap-md';
import { NgxEditorModule } from 'ngx-editor';

// Services
import { ApiService } from './api-service.service';
import { PersistentStorageService } from './persistent-storage.service';
import { WINDOW_SERVICES } from './window-service.service';
import { FooterComponent } from './footer/footer.component';
import { TruncateTextPipe } from './truncate-text.pipe';
import { LoginComponent } from './login/login.component';
import { AboutMeComponent } from './about-me/about-me.component';
import { ContactMeComponent } from './contact-me/contact-me.component';
import { PostDetailsComponent } from './post-details/post-details.component';
import {UtilsService} from './utils.service';
import { CreatePostComponent } from './create-post/create-post.component';
import { AdminComponent } from './admin/admin.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    ProfileComponent,
    SettingsComponent,
    FooterComponent,
    TruncateTextPipe,
    LoginComponent,
    AboutMeComponent,
    ContactMeComponent,
    PostDetailsComponent,
    CreatePostComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgxEditorModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [
    // Services go here
    PersistentStorageService,
    ApiService,
    WINDOW_SERVICES,
    UtilsService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
