import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { BehaviorSubject, catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from './enum/data-state.enum';
import { Priority } from './enum/priority.enum';
import { AppState } from './interface/app-state';
import { CustomResponse } from './interface/custom-response';
import { TaskService } from './service/task.service';
import { Task } from './interface/task';

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
  private isLoading = new BehaviorSubject<boolean>(false);

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

  addTask(taskForm: NgForm): void {
    this.isLoading.next(true);
    this.appState$ = this.taskService.add$(taskForm.value)
    .pipe(
      map(response => {
        this.dataSubject.next(
          {...response, data: { tasks: [response.data.task, ...this.dataSubject.value.data.tasks] }}
        );
        document.getElementById('closeModal').click();
        this.isLoading.next(false);
        taskForm.resetForm();
        return { dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }
      }),
      startWith({ dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }),
      catchError(( error: string ) => {
        this.isLoading.next(false);
        return of({ dataState: DataState.ERROR_STATE, error });
      })
    );
  }

  deleteTask(task: Task): void {
    this.appState$ = this.taskService.delete$(task.id)
    .pipe(
      map(response => {
        this.dataSubject.next(
          { ...response, data: 
            { tasks: this.dataSubject.value.data.tasks.filter(t => t.id !== task.id) }
          }
        );
        return { dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }
      }),
      startWith({ dataState: DataState.LOADED_STATE, appData: this.dataSubject.value }),
      catchError(( error: string ) => {
        return of({ dataState: DataState.ERROR_STATE, error });
      })
    );
  }

  printXLSReport(): void {
    let dataType = 'application/vnd.ms-excel.sheet.macroEnabled.12';
    let tableSelect = document.getElementById('tasks');
    let tableHtml = tableSelect.outerHTML.replace(/ /g, '%20');
    let downloadLink = document.createElement('a');
    document.body.appendChild(downloadLink);
    downloadLink.href = 'data:' + dataType + ', ' + tableHtml;
    downloadLink.download = 'tasks-report.xls';
    downloadLink.click();
    document.body.removeChild(downloadLink); 
  }

  printPDFReport(): void {
    window.print();
  }

}
