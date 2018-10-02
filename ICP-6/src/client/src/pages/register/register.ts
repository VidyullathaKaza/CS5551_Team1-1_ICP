import { Component, ViewChild } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {HomePage} from '../home/home'
import {LoginPage} from '../login/login'
import {TabsPage} from '../tabs/tabs'

/**
 * Generated class for the RegisterPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-register',
  templateUrl: 'register.html',
})
export class RegisterPage {
@ViewChild('username') emailId;
@ViewChild('fname') firstName;
@ViewChild('lname') lastName;
@ViewChild('password') pwd;
@ViewChild('confirmPwd') confirmPwd;

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad RegisterPage');
  }
  
register(){
var validate = true

if(this.emailId.value == "" || this.firstName.value == "" || this.lastName.value ==""|| this.pwd.value == "" || this.confirmPwd.value == "") 
{
alert("Fields should not be empty" )
validate = false;
}

if(!(this.pwd.value == this.confirmPwd.value)) 
{
alert("Password Should Match" )
validate = false;
}

if (validate == true){
this.navCtrl.push(TabsPage)
}

}



login(){
this.navCtrl.push(LoginPage)
}

}
