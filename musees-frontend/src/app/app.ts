import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  standalone: false,
  styleUrl: './app.scss',
  templateUrl: './app.html',
})
export class App implements OnInit {
  constructor(
    public readonly auth: AuthService,
    private readonly router: Router
  ) {}

  ngOnInit(): void {
    this.auth.loadToken();
    if (this.auth.getToken() == null || this.auth.isTokenExpired()) {
      this.router.navigate(['/login']);
    }
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
