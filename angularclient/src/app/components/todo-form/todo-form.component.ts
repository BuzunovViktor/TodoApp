import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from '@angular/router';
import { TodoService } from '../../service/todo-service.service';
import { ToDo } from '../../model/todo';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-todo-form',
  templateUrl: './todo-form.component.html',
  styleUrls: ['./todo-form.component.css']
})
export class TodoFormComponent implements OnInit {

  addForm: FormGroup;
  todo: ToDo;

  constructor(private route: ActivatedRoute, private router: Router, private todoService: TodoService, private formBuilder: FormBuilder) {
      this.todo = new ToDo();
    }

  onSubmit() {
    this.todoService.save(this.todo).subscribe(result => this.gotoTodoList());
  }

  gotoTodoList() {
    this.router.navigate(['/todo']);
  }

  ngOnInit() {
    this.addForm = this.formBuilder.group({
                name: ['', Validators.required],
                description: ['', Validators.required]
            });
  }

}
