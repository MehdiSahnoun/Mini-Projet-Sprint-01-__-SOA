import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  user: User = new User();
  err: number = 0;

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onLoggedin(): void {
    this.authService.login(this.user).subscribe({
      next: (data) => {
        let jwToken = data.headers.get('Authorization')!;
        this.authService.saveToken(jwToken);
        this.router.navigate(['/']);
      },
      error: (_err: any) => {
        this.err = 1;
      }
    });
  }

  // Alias for compatibility
  submit(): void {
    this.onLoggedin();
  }
}
