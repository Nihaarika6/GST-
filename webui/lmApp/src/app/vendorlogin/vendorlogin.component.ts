import { Component, OnInit } from '@angular/core';
import { VendorService } from '../vendor.service';
import { Observable} from 'rxjs';
import 'rxjs/add/observable/of';
import {Router} from '@angular/router';
import { Vendor } from '../vendor';

@Component({
  selector: 'app-vendorlogin',
  templateUrl: './vendorlogin.component.html',
  styleUrls: ['./vendorlogin.component.css']
})
export class VendorloginComponent implements OnInit {

  result : any = {res:''};
  test : string;
  vendId : number;
  userName : string;
  passWord : string;
  vendor : Observable<Vendor>;
  count : number;
  show: boolean;

  // custService : VendorService;
  password() {
    this.show = !this.show;
  }

  validate()  {
      this.vendorService.validateVendor(this.userName,this.passWord).subscribe( x => {
      if(x=="1") {
        localStorage.setItem("user",this.userName);
        localStorage.setItem("password",this.passWord);
        //  alert("Welcome to the Vendor Login Page");
        this.router.navigate(["/vendordashboard"]);
      } else {
        this.count++;
        
      }
     console.log("result " +x);
     },
    err => {
      console.log(err);
    })
  }
  
  
  constructor(private vendorService : VendorService, private router : Router) { 
    this.count=0;
  }

  ngOnInit() {
    this.validate();
  }

}