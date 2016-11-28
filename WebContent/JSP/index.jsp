<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="template/header.jsp" %>
<style>
/* GLOBAL STYLES
-------------------------------------------------- */
/* Padding below the footer and lighter body text */

body {
  padding-bottom: 40px;
  color: #5a5a5a;
}


/* CUSTOMIZE THE NAVBAR
-------------------------------------------------- */

/* Special class on .container surrounding .navbar, used for positioning it into place. */
.navbar-wrapper {
  position: absolute;
  top: 0;
  right: 0;
  left: 0;
  z-index: 20;
}

/* Flip around the padding for proper display in narrow viewports */
.navbar-wrapper > .container {
  padding-right: 0;
  padding-left: 0;
}
.navbar-wrapper .navbar {
  padding-right: 15px;
  padding-left: 15px;
}
.navbar-wrapper .navbar .container {
  width: auto;
}


/* CUSTOMIZE THE CAROUSEL
-------------------------------------------------- */

/* Carousel base class */
.carousel {
  height: 500px;
  margin-bottom: 60px;
}
/* Since positioning the image, we need to help out the caption */
.carousel-caption {
  z-index: 10;
}

/* Declare heights because of positioning of img element */
.carousel .item {
  height: 500px;
  background-color: #777;
}
.carousel-inner > .item > img {
  position: absolute;
  top: 0;
  left: 0;
  min-width: 100%;
  height: 500px;
}


/* MARKETING CONTENT
-------------------------------------------------- */

/* Center align the text within the three columns below the carousel */
.marketing .col-lg-4 {
  margin-bottom: 20px;
  text-align: center;
}
.marketing h2 {
  font-weight: normal;
}
.marketing .col-lg-4 p {
  margin-right: 10px;
  margin-left: 10px;
}


/* Featurettes
------------------------- */

.featurette-divider {
  margin: 80px 0; /* Space out the Bootstrap <hr> more */
}

/* Thin out the marketing headings */
.featurette-heading {
  font-weight: 300;
  line-height: 1;
  letter-spacing: -1px;
}


/* RESPONSIVE CSS
-------------------------------------------------- */

@media (min-width: 768px) {
  /* Navbar positioning foo */
  .navbar-wrapper {
    margin-top: 20px;
  }
  .navbar-wrapper .container {
    padding-right: 15px;
    padding-left: 15px;
  }
  .navbar-wrapper .navbar {
    padding-right: 0;
    padding-left: 0;
  }

  /* The navbar becomes detached from the top, so we round the corners */
  .navbar-wrapper .navbar {
    border-radius: 4px;
  }

  /* Bump up size of carousel content */
  .carousel-caption p {
    margin-bottom: 20px;
    font-size: 21px;
    line-height: 1.4;
  }

  .featurette-heading {
    font-size: 50px;
  }
}

@media (min-width: 992px) {
  .featurette-heading {
    margin-top: 120px;
  }
}
</style>
 <!-- Carousel
    ================================================== -->
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
      <!-- Indicators -->
      <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
      </ol>
      <div class="carousel-inner" role="listbox">
        <div class="item active">
          <!-- 
          <img class="first-slide" src="" alt="First slide">
           -->
          <div class="container">
            <div class="carousel-caption">
              <h1>NewsCrawler</h1>
              <p>뉴스크롤러, <strong>뉴스를 보는 새로운 관점</strong><br>저희의 목표는 당신이 빠르고 재미있게 기사의 흐름을 읽는 것입니다.</p>
              <p><a class="btn btn-lg btn-primary" href="/Semantic" role="button"><i class="fa fa-newspaper-o" aria-hidden="true"></i> 보러가기</a></p>
            </div>
          </div>
        </div>
        <div class="item">
          <!-- 
          <img class="second-slide" src="" alt="Second slide">
           -->
          <div class="container">
            <div class="carousel-caption">
              <h1>Crawler + Analytics + Algorithm</h1>
              <p>뉴스를 수집해 상위 키워드 50개를 선별하고 이를 알고리즘을 통해 의미망으로 만들어 냅니다.<br>GtiHub에서 동작 원리를 확인할 수 있습니다.</p>
              <p><a class="btn btn-lg btn-primary" href="https://github.com/NewsCrawler/" role="button"><i class="fa fa-github" aria-hidden="true"></i> GitHub</a></p>
            </div>
          </div>
        </div>
      </div>
      <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
    </div><!-- /.carousel -->


    <!-- Marketing messaging and featurettes
    ================================================== -->
    <!-- Wrap the rest of the page in another container to center all the content. -->

    <div class="container marketing">

      <!-- Three columns of text below the carousel -->
      <div class="row">
        <div class="col-lg-4">
          <img class="img-circle" src="/res/img/item1.png" alt="Generic placeholder image" width="140" height="140">
          <h2>실시간의 다양한 기사들</h2>
          <p>매 시간 갱신되는 뉴스 기사들 속에서 빠르게 핵심을 골라냅니다. 수백 개의 기사들 안에서 가장 많이 언급된 핵심단어 50개를 보여줍니다.</p>
          <p><a class="btn btn-default" href="/Title" role="button">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="/res/img/item2.png" alt="Generic placeholder image" width="140" height="140">
          <h2>단 한번의 클릭!</h2>
          <p>핵심 단어 50개를 보여주고 가중치에 따라 크기를 부여했습니다. 단어에 마우스를 가져다 대면 관련 단어와 관련 있는 단어들을 알 수 있고 이 단어가 언급된 기사를 볼 수 있습니다.</p>
          <p><a class="btn btn-default" href="/Semantic" role="button">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
        <div class="col-lg-4">
          <img class="img-circle" src="/res/img/item3.png" alt="Generic placeholder image" width="140" height="140">
          <h2>새로운 재미</h2>
          <p>기존의 신문기사 형식과 다른 형식의 뉴스 포털 사이트이다. 다채로운 색깔로 이루어진 그래프가 사용자들의 흥미를 불러일으킨다.</p>
          <p><a class="btn btn-default" href="/Semantic" role="button">View details &raquo;</a></p>
        </div><!-- /.col-lg-4 -->
      </div><!-- /.row -->


      <!-- START THE FEATURETTES -->

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading">'최순실'과 '청와대' 키워드에대한 <span class="text-muted">정보들이 궁금하다면? </span></h2>
          <p class="lead">네이버 뉴스 속보를 중심으로 다양한 언론사들에서 자주 언급된 단어나 문맥상 의미를 파악해 키워드로 추려냅니다. 키워드에 관한 뉴스를 날짜에 맞춰 확인할 수 있습니다.</p>
        </div>
        <div class="col-md-5">
          <img class="featurette-image img-responsive center-block" data-src="/res/img/feature2.png" src="/res/img/feature2.png" alt="Generic placeholder image">
        </div>
      </div>

      <hr class="featurette-divider">

      <div class="row featurette">
        <div class="col-md-7 col-md-push-5">
          <h2 class="featurette-heading">2016년 11월 11일의 <span class="text-muted">의미망 분석 그래프가 보고 싶다면?</span></h2>
          <p class="lead">원 안의 단어는 최신 기사들 속에서 가장 많이 언급된 50개의 단어를 키워드로 선정한 것이며 각 단어를 선택하면 다른 키워드들 간의 관계를 알 수 있습니다.</p>
        </div>
        <div class="col-md-5 col-md-pull-7">
          <img class="featurette-image img-responsive center-block" data-src="/res/img/feature1.png" src="/res/img/feature1.png" alt="Generic placeholder image">
        </div>
      </div>

      <!-- /END THE FEATURETTES -->
<%@ include file="template/footer.jsp" %>