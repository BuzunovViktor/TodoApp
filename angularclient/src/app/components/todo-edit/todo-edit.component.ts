import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router} from '@angular/router';
import { TodoService } from '../../service/todo-service.service';
import { ToDo } from '../../model/todo';

@Component({
  selector: 'app-todo-edit',
  templateUrl: './todo-edit.component.html',
  styleUrls: ['./todo-edit.component.css']
})
export class TodoEditComponent implements OnInit {

  editForm: FormGroup;
  id: number;
  todo: ToDo;

  constructor(private route: ActivatedRoute, private router: Router, private todoService: TodoService, private formBuilder: FormBuilder) {
    this.id = route.snapshot.params['id'];
    this.todoService.getById(this.id).subscribe(data => {
      this.todo = data;
    });
  }

  onSubmit() {
    this.todoService.update(this.id, this.todo).subscribe(data => {
      this.gotoTodoList();
    });
  }

  gotoTodoList() {
    this.router.navigate(['/todo']);
  }

  ngOnInit() {
    this.editForm = this.formBuilder.group({
                name: ['', Validators.required],
                description: ['', Validators.required]
            });
  }

}
