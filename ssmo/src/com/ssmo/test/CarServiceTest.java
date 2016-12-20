package com.ssmo.test;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssmo.pojo.Car;
import com.ssmo.service.CarService;
import com.ssmo.util.Pager;

public class CarServiceTest {
	private CarService carService;
	
	@SuppressWarnings("resource")
	@Before
	public void init(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		carService = ctx.getBean("carService", CarService.class);
	}
	
	@Test
	public void addCar(){
		Car car=new Car();
		car.setName("Golf");
		car.setSaleDate(new Date());
		car.setPrice(800000d);
		int c=carService.add(car);
		if(c>0){
			System.out.println("OK");
		}else{
			System.out.println("error");
		}
	}
	
	@Test
	public void find(){
		Car car=carService.find(3);
		System.out.println(car.getName()+" "+car.getPrice()+" "+car.getSaleDate());
	}
	
	@Test
	public void findAll(){
		List<Car> cars=carService.find();
		for(Car car:cars){
			System.out.println(car.getName()+" "+car.getPrice()+" "+car.getSaleDate());
		}
	}
	
	@Test
	public void modify(){
		Car car=new Car();
		car.setId(10);
		car.setName("玛莎拉蒂");
		car.setSaleDate(new Date());
		car.setPrice(500000d);
		if(carService.modify(car)>0){
			System.out.println("OK");
		}else{
			System.out.println("error");
		}
	}
	
	@Test
	public void remove(){
		if(carService.remove(9)>0){
			System.out.println("OK");
		}else{
			System.out.println("error");
		}
	}
	
	@Test
	public void findPager() {
		//easyui必需的参数
		Integer page=2;
		Integer rows=4;
		String sort="id";
		String order="asc";
		
		//条件参数（可选）
		String name=null;
		Date beginDate=null;
		Date endDate=null;
		
		//name="%拉%";
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		try {
			beginDate = sdf.parse("2016-12-10 00:00:00");
			endDate = sdf.parse("2016-12-20 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		
		//计算分页的起始页和结束页
		Integer pageno=(page-1)*rows;
		Integer pagesize=page*rows;
		
		Pager<Car> pager=carService.findPager(pageno, pagesize, sort, order, name, beginDate, endDate);
		
		System.out.println("总记录数："+pager.getTotal());
		for(Car car:pager.getRows()){
			System.out.println(car.getId()+" "+car.getName()+" "+car.getPrice()+" "+car.getSaleDate());
		}
	}
}
