import { Component, OnInit } from '@angular/core';
import { ToDo } from '../../model/todo';
import { TodoService } from '../../service/todo-service.service';
import { ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  todos: ToDo[];

  constructor(private todoService: TodoService, private router: Router) { }

  ngOnInit() {
  this.todoService.findAll().subscribe(data => {
        this.todos = data;
      });
  }

  delete(id: number) {
    this.todoService.delete(id).subscribe(result => this.gotoTodoList());
  }

  gotoTodoList() {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()=>
    this.router.navigate(['/todo']));
  }

}
