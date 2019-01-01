import { Injectable, Inject } from '@angular/core';
import {WINDOW} from './window-service.service';


@Injectable({
  providedIn: 'root'
})
export class PersistentStorageService {

  constructor(@Inject(WINDOW) private window: Window) {
  }

  public getValue(key: string): Promise<string> {
    return new Promise<string>((resolve) => {
      const value = this.window.localStorage.getItem(key) as string;
      resolve(value);
    });
  }

  public setValue(key: string, value: string): void {
    this.window.localStorage.setItem(key, value);
  }

  public deleteValue(key: string): void {
    this.window.localStorage.removeItem(key);
  }
}
