import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../../app.component';
import {ActivatedRoute, Router} from '@angular/router';
import {UrlService} from '../../service/url.service';
import {GroupService} from '../../service/group.service';
import {TokenProviderService} from '../../service/token.provider.service';
import {GradebookService} from '../../service/gradebook.service';
import {Assessment} from '../../dto/Assessment';

@Component({
  selector: 'app-gradebook',
  templateUrl: './gradebook.component.html',
  styleUrls: ['./gradebook.component.scss']
})
export class GradebookComponent implements OnInit {

  num: number;
  assessments: Assessment[];

  constructor(private app: AppComponent,
              private router: Router,
              private route: ActivatedRoute,
              private urlService: UrlService,
              private gradebookService: GradebookService,
              private tokenProviderService: TokenProviderService) { }

  ngOnInit() {
    this.num = 0;
    this.tokenProviderService.token.subscribe(token => {
      console.log(token);
      this.gradebookService.getGradebookAssessments(token).subscribe(assessments => {
        this.assessments = assessments;
      });
    });
  }

}
