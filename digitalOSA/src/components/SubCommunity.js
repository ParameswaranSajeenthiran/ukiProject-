
import React, { Component } from "react";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import Typography from '@material-ui/core/Typography';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import Container from "./Container";
import { TextField,Button,FormControl } from "@material-ui/core";
import CreateSubCommunity from "./CreateSubCommunity";



import GoogleChart from "./GoogleCharts";
import SearchCommunity from "./SearchCommunity";
import { RadioButtonCheckedTwoTone, RecentActorsRounded } from "@material-ui/icons";
const style = {
    paper: {
      flexGrow: 1,
     backgroundColor:"inherit",
      color: 'black'
    },
    menuButton: {
      spacing: 2,
    },
    link: {
      underline: 'none'
    }
   
  }
  
  
  const subCommunity=[
    {name:"A/L 2020",
    id:1},
    {
      name:"chess club",
      id:2
    },
    {
      name:"Ahtletic club",
      id:3
    },
    {name:"O/l 2017 batch",
    id:4
  },
  {name:"Scouting",
  id:5
  }
  ]
class SubCommunity extends Component{
    constructor(props){
        super(props);
    
        }

        render(){
            return (

           <div>
              <SearchCommunity/>
                <div>
                
                  <GoogleChart/>
                  <div>
                   
<section id="services">
      <div class="container wow fadeInUp">
        <div class="row">
          <div class="col-md-12">
            <h3 class="section-title">Explore Sub Communities</h3>
            <div class="section-title-divider"></div>
            <p class="section-description">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium</p>
          </div>
        </div>

        <div class="row">
        {subCommunity.map( community=> {return (
         
          <div class="col-lg-4 col-md-6 service-item">
            <div class="service-icon"><i class="fa fa-desktop"></i></div>
            <h4 class="service-title"><a href="">{community.name}</a></h4>
            <p class="service-description">Voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident</p>
            <Button href="/community"  onClick={this.changeSubCommunity}name={community.name} variant="contained" color="primary">join subCommunity</Button>
          </div>
        
          
        )})}

</div>
       
      </div>
    </section>

     </div>
                  
             
               
              
             </div>
             </div>
         
                 );
        }
    }
    export default SubCommunity
  
