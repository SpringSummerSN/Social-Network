import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const AUTH_API = 'http://localhost:8080/api/v1/';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
}

@Injectable({
  providedIn: 'root'
})

// AuthService sends registration, login, logout HTTP POST requests to backend
export class AuthService {
  constructor(private http: HttpClient) {
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'login',
      {
        email,
        password,
      },
      httpOptions
    )
  }

  register(name: string, surname: string, email: string, password: string): Observable<any> {
    return this.http.post(
      AUTH_API + 'register',
      {
        name,
        surname,
        email,
        password,
      },
      httpOptions
    )
  }

  logout(): Observable<any> {
    return this.http.post(
      AUTH_API + 'signout',
      {},
      httpOptions
    )
  }
}
