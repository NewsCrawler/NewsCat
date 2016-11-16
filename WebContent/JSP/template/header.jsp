<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <meta property="og:type" content="article">
    <meta property="og:url" content="http://news.p316.net">
    <meta property="og:image" content="http://news.p316.net/res/img/news.jpg">
    <meta property="og:title" content="뉴스 크롤러">
    <meta property="og:description" content="뉴스를 보는 새로운 관점">

    <meta property="me2:image" content="http://decha.p316.net/000.png">

    <meta name="twitter:card" content="summary_large_image">
    <meta name="twitter:creator" content="누군">
    <meta name="twitter:site" content="뉴스 크롤러">
    <meta name="twitter:title" content="뉴스 크롤러">
    <meta name="twitter:image" content="http://news.p316.net/res/img/news.jpg">
    <meta name="twitter:description" content="뉴스를 보는 새로운 관점">

    <title>뉴스 크롤러</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <link rel="stylesheet" href="/res/css/style.css">
  </head>
  <body>
    <!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="/">뉴스 크롤러</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="Semantic">의미망분석</a></li>
            <li ><a href="/Title">수집기사</a></li>
            <li ><a href="/About">개발기록</a></li>
            <li ><a href="https://github.com/NewsCrawler/" target="blank">GitHub</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
  