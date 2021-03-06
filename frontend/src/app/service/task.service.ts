import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';
import { Priority } from '../enum/priority.enum';
import { CustomResponse } from '../interface/custom-response';

@Injectable({ providedIn: 'root' })
export class TaskService 
{
  private readonly apiUrl = 'http://localhost:8080';
  
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

  filterByPriority$ = (priority: Priority, response: CustomResponse) => <Observable<CustomResponse>>
  new Observable<CustomResponse>(
    subscriber => {
      console.log(response);
      subscriber.next(
        priority === Priority.ALL ? 
          { ...response, message: `Tasks filtradas por prioridade: ${priority}` } :
          {
            ...response,
            message: response.data.tasks.filter(task => task.priority === priority).length > 0 ?
            `Tasks filtradas pela prioridade: ${priority}` : `Não há tasks com o status: ${priority}`,
            data: { tasks: response.data.tasks.filter(task => task.priority === priority) }
          }
      );
      subscriber.complete();
    }
  )
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

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return throwError(() => `Um erro ocorreu - Código do erro: ${error.status}`);
  }
}
