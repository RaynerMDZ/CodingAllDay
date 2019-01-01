import { Injectable } from '@angular/core';
import * as momentjs from 'moment';

@Injectable({
  providedIn: 'root'
})
export class UtilsService {

  constructor() { }

  public formatDate(date: any): string {
    return momentjs.utc(date).local().fromNow();
  }
}
