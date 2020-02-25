import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Menu } from '../menu';
import { MenuService } from '../menu.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menu : Observable<Menu[]>
  menu1 : Observable<Menu>
  menuId : number;
  // show() {
  //   this.menu1=this.menuService.searchMenu(this.menuId);
  // }
  constructor(private menuService : MenuService) { 
    this.menu=menuService.showMenu();
    // this.menu1=menuService.searchMenu(this.menuId);
  }
  ngOnInit() {
  }

}
