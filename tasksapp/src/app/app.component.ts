import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BehaviorSubject, catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from './enum/data-state.enum';
import { Priority } from './enum/priority.enum';
import { AppState } from './interface/app-state';
import { CustomResponse } from './interface/custom-response';
import { TaskService } from './service/task.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {

  appState$: Observable<AppState<CustomResponse>>;

  readonly DataState = DataState;
  readonly Priority = Priority;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private filterSubject = new BehaviorSubject<string>('');
  private isLoading = new BehaviorSubject<boolean>(false);

  filterStatus$ = this.filterSubject.asObservable();
  isLoading$ = this.isLoading.asObservable();

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.appState$ = this.taskService.tasks$.pipe(
      map( response => {
        this.dataSubject.next(response);
        return { dataState: DataState.LOADED_STATE, appData: response };
      }),
      startWith({ dataState: DataState.LOADING_STATE }),
      catchError((error: string) => {
        return of({ dataState: DataState.ERROR_STATE, error });
      })
    );
  }

  filterTasksByPriority(priority: Priority): void {
    this.appState$ = this.taskService.filterByPriority$(priority, this.dataSubject.value)
    .pipe(
      map(response => {
        return { dataState: DataState.LOADED_STATE, appData: response }
      }),
      startWith({ dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }),
      catchError(( error: string ) => {
        return of({ dataState: DataState.ERROR_STATE, error });
      })
    );
  }
}
