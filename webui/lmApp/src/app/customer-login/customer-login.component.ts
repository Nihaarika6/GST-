import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import 'rxjs/add/observable/of';
import { Customer } from '../customer';
import { CustomerService } from '../customer.service';
@Component({
  selector: 'app-customer-login',
  templateUrl: './customer-login.component.html',
  styleUrls: ['./customer-login.component.css']
})
export class CustomerLoginComponent implements OnInit {

  result : any = {res:''};
  test : string;
  customerId : number;
  userName : string;
  passWord : string;
  customer : Observable<Customer>;
  count : number;
  // custService : CustomerService;
  show : boolean;
  password() {
    this.show = !this.show;
  }

  validate()  {
    
      this.customerService.validateCustomer(this.userName,this.passWord).subscribe( x => {
      if(x=="1") {
        localStorage.setItem("user",this.userName);
        localStorage.setItem("customerId",this.passWord);
        this.router.navigate(["/dashBoard"]);
      } else {
        this.count++;
       
      }
     console.log("result " +x);
      // this.result.res=x;
      // this.test = x;
     },
    err => {
      console.log(err);
    })
  }
  
  
  constructor(private customerService : CustomerService, private router : Router) { 
    this.count=0;
  }

  ngOnInit() {
    this.validate();
  }

}
