import { Component,ViewChild } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import {HomePage} from '../home/home'
import {RegisterPage} from '../register/register'
import {TabsPage} from '../tabs/tabs'
/**
 * Generated class for the LoginPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-login',
  templateUrl: 'login.html',
})
export class LoginPage {
@ViewChild('username') uname;
@ViewChild('password') pwd;
  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }
  
signIn(){
var validate = true

if(!(this.uname.value == "admin" && this.pwd.value == "admin"))
{
alert("Invalid Credentials")
validate= false
}

if(this.uname.value == "" || this.pwd.value == ""){
alert("Please enter the credentials")
validate= false
}


if (validate == true){
this.navCtrl.push(TabsPage)
}
}

register(){
this.navCtrl.push(RegisterPage)
}

  ionViewDidLoad() {
    console.log('ionViewDidLoad LoginPage');
  }

}
