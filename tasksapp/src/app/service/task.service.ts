import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { CustomResponse } from '../interface/custom-response';

@Injectable({ providedIn: 'root' })
export class TaskService {
  private readonly apiUrl = 'any';
  
  constructor(private http: HttpClient) { }
  
  tasks$ = <Observable<CustomResponse>>
  this.http.get<CustomResponse>(`${this.apiUrl}/task/list`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );

  add$ = (task: Task) => <Observable<CustomResponse>>
  this.http.post<CustomResponse>(`${this.apiUrl}/task/add`, task)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );

  get$ = (taskId: number) => <Observable<CustomResponse>>
  this.http.get<CustomResponse>(`${this.apiUrl}/task/get/${taskId}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );

  delete$ = (taskId: number) => <Observable<CustomResponse>>
  this.http.delete<CustomResponse>(`${this.apiUrl}/task/delete/${taskId}`)
  .pipe(
    tap(console.log),
    catchError(this.handleError)
  );

  handleError(handleError: any): Observable<never> {
    return throwError ('Method not implemented.');
  }
}
