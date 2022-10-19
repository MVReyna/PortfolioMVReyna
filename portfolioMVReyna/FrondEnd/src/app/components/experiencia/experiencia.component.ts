import { Component, OnInit } from '@angular/core';
import { Experiencia } from 'src/app/Model/experiencia';
import { SExperienicaService } from 'src/app/service/s-experienica.service';
import { TokenService } from 'src/app/service/token.service';

@Component({
  selector: 'app-experiencia',
  templateUrl: './experiencia.component.html',
  styleUrls: ['./experiencia.component.css']
})
export class ExperienciaComponent implements OnInit {
  experiencia: Experiencia[]=[];

  constructor(private sExperiencia:SExperienicaService, private tokenService: TokenService) { }

  isLogged= false;

  ngOnInit(): void {
    this.cargarExperiencia();
    if(this.tokenService.getToken()){
      this.isLogged= true;
    } else{
      this.isLogged= false;
    }
  }


  
cargarExperiencia(): void {
  this.sExperiencia.lista().subscribe(data =>{this.experiencia=data});
}


}

