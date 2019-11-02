import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';
import { SearchComponent } from './component/search/search.component';
import { LoginComponent } from './component/login/login.component';

const routes: Routes = [
  {
    path: 'search',
    // canActivate: [AuthGuard],
    component: SearchComponent
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  { path: '', redirectTo: '/search', pathMatch: 'full' },
  { path: '**', redirectTo: '/search' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
