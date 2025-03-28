const hd_dropDown_event = function(){
    const hd = document.querySelector(".hd");
    const menu_wrap = hd.querySelector(".bottom-menu-wrap");
    const menu_list = menu_wrap.querySelector(".main-menu");
    const list = menu_wrap.querySelectorAll(".main-menu > li");
    const sub_menu_list = menu_wrap.querySelector(".sub-menu-wrap");    
    
    
    
    for(let i = 0; i < list.length; i++){        
        list[i].addEventListener("mouseover",function(){
            if(this.classList.contains("area-menu")){
                console.log(i)
				if(sub_menu_list){
                	sub_menu_list.classList.add("active")
				}
                menu_list.classList.remove("active")
            }else{
				
                	menu_list.classList.add("active")
				
				if(sub_menu_list){
                	sub_menu_list.classList.remove("active")
				}
            }
            
        })
    }
    hd.addEventListener("mouseleave",function(){
		if(sub_menu_list){         
        	sub_menu_list.classList.remove("active")
		}
        menu_list.classList.remove("active")        
    })
    
}

const hd_areaSelect_event = function(){
    const form_container = document.querySelector(".area-select-form");
    const checkbox_list_wrap = form_container.querySelector(".checkbox-list-wrap");
    const checkbox_list = checkbox_list_wrap.querySelectorAll(".checkbox-list");
    const checked_class = "checked";
    
    for(i=0; i<checkbox_list.length; i++) {             
        
        checkbox_list[i].addEventListener("click",function(){                                         
            for(j=0; j<checkbox_list.length; j++) {     
                checkbox_list[j].classList.remove(checked_class);
            }
            this.classList.add(checked_class); 
        })            
    }
}
// hd_areaSelect_event();
hd_dropDown_event();


//메인페이지 슬라이드
const basic_slide_ui = function(){                    
    const basicSwiper = new Swiper(".top-slide-wrap",{
        effect: 'fade',
        fadeEffact: {
            crossFade: true
        },
        speed: 500,
        loop: true,                                 
        autoplay: {
            delay: 3000,      
            disableOnInteraction: false          
        },  
        pagination: {
            el: ".top-slide-wrap + .swiper-pagination",
        },
        navigation: {
            prevEl: ".top-slide-section .slide-btn-wrap .prev",
            nextEl: ".top-slide-section .slide-btn-wrap .next",                
        },                          
    })     
}
if(document.querySelectorAll(".top-slide-wrap") != null){
    basic_slide_ui()
}

const list_info_slide = function(){
    const basicSwiper = new Swiper(".list-info-wrap",{
        speed: 500,
        loop: true,                                 
        autoplay: {
            delay: 3000,                              
        },  
        slidesPerView: 4,
        spaceBetween: 30,
        pagination: {
            el: ".list-info-wrap + .swiper-pagination",
        },
        navigation: {
            prevEl: ".list-info-wrap + .slide-btn-wrap .prev",
            nextEl: ".list-info-wrap + .slide-btn-wrap .next",                
        }
    })
}
if(document.querySelectorAll(".list-info-wrap") != null && document.querySelectorAll(".list-info-wrap1").length == 0){
    list_info_slide()
}

const list_info_slide1 = function(){
    const basicSwiper = new Swiper(".list-info-wrap1",{
        speed: 500,
        loop: true,                                 
        autoplay: {
            delay: 3000,                              
        },  
        slidesPerView: 4,
        spaceBetween: 30,
        pagination: {
            el: ".list-info-wrap1 + .swiper-pagination",
        },
        navigation: {
            prevEl: ".list-info-wrap1 + .slide-btn-wrap .prev1",
            nextEl: ".list-info-wrap1 + .slide-btn-wrap .next1",                
        }
    })
}
if(document.querySelectorAll(".list-info-wrap1") != null){
    list_info_slide1()
}

const list_info_slide2 = function(){
    const basicSwiper = new Swiper(".list-info-wrap2",{
        speed: 500,
        loop: true,                                 
        autoplay: {
            delay: 3000,                              
        },  
        slidesPerView: 4,
        spaceBetween: 30,
        pagination: {
            el: ".list-info-wrap + .swiper-pagination",
        },
        navigation: {
            prevEl: ".list-info-wrap2 + .slide-btn-wrap .prev2",
            nextEl: ".list-info-wrap2 + .slide-btn-wrap .next2",                
        }
    })
}
if(document.querySelectorAll(".list-info-wrap2") != null){
    list_info_slide2()
}

const list_info_slide3 = function(){
    const basicSwiper = new Swiper(".list-info-wrap3",{
        speed: 500,
        loop: true,                                 
        autoplay: {
            delay: 3000,                              
        },  
        slidesPerView: 4,
        spaceBetween: 30,
        pagination: {
            el: ".list-info-wrap3 + .swiper-pagination",
        },
        navigation: {
            prevEl: ".list-info-wrap3 + .slide-btn-wrap .prev",
            nextEl: ".list-info-wrap3 + .slide-btn-wrap .next",                
        }
    })
}
if(document.querySelectorAll(".list-info-wrap3") != null){
    list_info_slide3()
}