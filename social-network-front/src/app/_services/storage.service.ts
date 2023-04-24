import {Injectable} from '@angular/core';

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})

// StorageService manages user information in browser session storage
export class StorageService {
  constructor() {
  }

  // clear current session storage
  clean(): void {
    window.sessionStorage.clear();
  }

  // reset user for current session
  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  // return user for current session
  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }

  // check if user for current session exists
  public isLoggedIn(): boolean {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return true;
    }

    return false;
  }
}
