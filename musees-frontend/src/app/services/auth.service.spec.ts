import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

describe('AuthService', () => {
  let auth: AuthService;
  let routerSpy: { navigate: any };

  beforeEach(() => {
    routerSpy = { navigate: vi.fn() };
    localStorage.clear();

    TestBed.configureTestingModule({
      providers: [
        AuthService,
        provideHttpClient(),
        { provide: Router, useValue: routerSpy }
      ]
    });

    auth = TestBed.inject(AuthService);
  });

  it('identifies the administrator based on roles', () => {
    auth.roles = ['ADMIN', 'USER'];
    auth.isloggedIn = true;
    expect(auth.isAdmin()).toBe(true);
    expect(auth.isLoggedIn()).toBe(true);
  });

  it('gives standard accounts read-only access', () => {
    auth.roles = ['USER'];
    auth.isloggedIn = true;
    expect(auth.isAdmin()).toBe(false);
  });

  it('clears token on logout and navigates to login', () => {
    localStorage.setItem('jwt', 'test.token');
    auth.token = 'test.token';
    auth.isloggedIn = true;

    auth.logout();

    expect(auth.isLoggedIn()).toBe(false);
    expect(localStorage.getItem('jwt')).toBeNull();
    expect(routerSpy.navigate).toHaveBeenCalledWith(['/login']);
  });
});
