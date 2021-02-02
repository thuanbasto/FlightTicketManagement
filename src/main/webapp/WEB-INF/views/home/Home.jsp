<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="container"><h1 style="text-align: center;color: teal;">Home Page</h1></div>

<%-- <div class="container" style="margin-top: 50px">
	<h2>Customer Manager</h2>
	<form method="get" action="search">
		<input type="text" name="keyword" /> &nbsp; <input type="submit"
			value="Search" />
	</form>
	<h3>
		<a href="/new">New Customer</a>
	</h3>
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>E-mail</th>
			<th>Address</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${listCity}" var="city">
			<tr>
				<td>${city.city_Id}</td>
				<td>${city.name}</td>
			</tr>
		</c:forEach>
	</table>
</div> --%>

 <!--  <div class="container ">
    <div class="row" style="margin-top:70px;">
      <div class="col-sm-7">

        <ul class="nav nav-tabs">
          <li class="nav-item">
            <a id="ftab" class="nav-link"
              onclick="$(this).removeClass('tab_no_select_co');$(this).addClass('tab_select_co');$('#stab').addClass('tab_no_select_co');"
              data-toggle="tab" href="#home" style="border-color: white white transparent white;">Book your trip</a>
          </li>
          <li class="nav-item">
            <a id="stab" class="nav-link"
              onclick="$(this).removeClass('tab_no_select_co');$(this).addClass('tab_select_co');$('#ftab').addClass('tab_no_select_co');"
              data-toggle="tab" href="#menu1" style="border-color: white white transparent transparent;">Manage my
              bookings</a>
          </li>
        </ul>
        <script>
          $(document).ready(function () {
            $('#ftab').trigger('click');
          });
        </script>

        <div class="tab-content"
          style="border: 1px solid #F7F7F7;border-radius: 0 4px 4px 4px;padding: 40px 10px 60px 10px;background-color:#f7c4ff;padding-bottom: 20px;">

          <div id="home" class="tab-pane fade in active">
            <div class="form-group">
              <div class="fromto">
                <div class="form-group chieu">
                  <ul data-tag="channelList" style="list-style-type: none">
                    <li value=1 class="selected" data-id="ow">ONE WAY</li>
                    <li value=2 data-id="rt">ROUND TRIP</li>
                    <li value=3 data-id="md">Multiple Destinations</li>
                  </ul>
                  <script>
                    $(document).ready(function () {
                      $('#ngayden').hide();
                      $('ul[data-tag="channelList"] > li').click(function () {

                        $('ul[data-tag="channelList"] li').each(function () {
                          if ($(this).hasClass('selected')) {
                            $(this).removeClass("selected");
                          }
                        });

                        $(this).addClass("selected");
                        if ($(this).data('id') == 'ow') {
                          $('#ngayden').hide();
                        } else {
                          $('#ngayden').show();
                        }
                        console.log($(this).val());
                      });
                    });
                  </script>

                </div>
              </div>


              <div class="fromto">
                <div class="row">
                  <div class="col">
                    <div class="form-group">
                      <select class="form-control">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                      </select>
                    </div>
                  </div>
                  <div class="col">
                    <div class="form-group">
                      <select class="form-control">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                      </select>
                    </div>
                  </div>
                </div>
              </div>

              <div class="option_passenger&class">
                <div class="row">
                  <div class="col-sm-6">

                    <div class="dropdown">
                      <button type="button" class="btn btn-primary dropdown-toggle" 
                        style="background-image: linear-gradient(to right top, #eca9ff, #c4b1ff, #95b8ff, #5fbeff, #00c1ff);border-color: white;">
                        Passengers
                      </button>
                      <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Link 1</a>
                        <a class="dropdown-item" href="#">Link 2</a>
                        <a class="dropdown-item" href="#">Link 3</a>
                      </div>
                    </div>
                    <script>
                      
                    </script>

                  </div>
                  <div class="col-sm-6">
                    <div class="form-group">
                      <select class="form-control">
                        <option>Economy</option>
                        <option>Premium Economy</option>
                        <option>Business</option>
                      </select>
                    </div>
                  </div>
                </div>
              </div>

              <div class="ngaydi&ngayden">
                <div class="row">
                  <div class="form-group col-sm-6">
                    <input type="datetime-local" class="form-control" id="ngaydi">
                  </div>
                  <div class="form-group col-sm-6">
                    <input type="datetime-local" class="form-control" id="ngayden">
                  </div>
                </div>
                <div class="checkbox">
                  <label><input type="checkbox" value="" style="font-size: smaller;"> My dates are flexible / Lowest
                    fares</label>
                </div>
              </div>

              <div class="search">
              <div class="form-group mb-4">
                <button class="btn float-right"
                  style="background-image: linear-gradient(to right top, #eca9ff, #c4b1ff, #95b8ff, #5fbeff, #00c1ff);border-color: white;">Search</button>
              </div>
              </div>

            </div>
          </div>

          <div id="menu1" class="tab-pane fade">
            <h3>Menu 1</h3>
            <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
              consequat.</p>
          </div>


        </div>

      </div>

      <div class="col-sm-5" style="background-color:orange;">50%</div>

    </div>
  </div> -->