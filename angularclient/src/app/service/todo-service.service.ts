import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { ToDo } from '../model/todo';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class TodoService {

  private todoUrl: string;

  constructor(private http: HttpClient) {
    this.todoUrl = '/todo';
  }

  public findAll(): Observable<ToDo[]> {
    return this.http.get<ToDo[]>(this.todoUrl);
  }

  public getById(id: number): Observable<ToDo> {
    return this.http.get<ToDo>(this.todoUrl + '/' + id);
  }

  public save(todo: ToDo) {
    return this.http.post<ToDo>(this.todoUrl, todo);
  }

  public update(id: number, todo: ToDo) {
    const urlParams = new HttpParams().set("id", id.toString());
    return this.http.put(this.todoUrl, todo, { params: urlParams});
  }

  public delete(id: number){
    return this.http.delete(this.todoUrl + '/' + id);
  }

}
