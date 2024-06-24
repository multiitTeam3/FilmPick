package com.multi.mini.config;

import com.multi.mini.movie.model.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.multi.mini", annotationClass = Mapper.class)
public class MybatisConfig {
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessoinFactory(DataSource datasource) throws Exception{
        SqlSessionFactoryBean seb  = new SqlSessionFactoryBean();
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*.xml");

        seb.setMapperLocations(res);

        seb.setDataSource(datasource);

        org.apache.ibatis.session.Configuration configuration = new  org.apache.ibatis.session.Configuration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);

        // Type Aliases 설정
        configuration.getTypeAliasRegistry().registerAlias("memberDTO", com.multi.mini.member.model.dto.MemberDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("boardDTO", com.multi.mini.community.model.dto.BoardDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("pageDTO", com.multi.mini.common.model.dto.PageDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("roleDTO", com.multi.mini.member.model.dto.RoleDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("memberAndRoleDTO", com.multi.mini.member.model.dto.MemberAndRoleDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("couponDTO", com.multi.mini.admin.coupon.model.dto.CouponDTO.class);


//        configuration.getTypeAliasRegistry().registerAlias("noticeDTO", com.multi.mini.admin.notice.model.dto.NoticeDTO.class);
        

        
        //movie dto 추가
        configuration.getTypeAliasRegistry().registerAlias("movieDTO", MovieDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("regionDTO", RegionDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("cinemaDTO", CinemaDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("seatDTO", SeatDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("reservationDataDTO", ReservationDataDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("reservationDTO", ReservationDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("vwResDataDTO", VWResDataDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("vwResDataGroupDTO", VWResDataGroupDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("vwRewDataDTO", VWRewDataDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("reviewDTO", ReviewDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("moviePageDTO", MoviePageDTO.class);
        
        configuration.getTypeAliasRegistry().registerAlias("vwMovieManageDataDTO", VWMovieManageDataDTO.class);
        configuration.getTypeAliasRegistry().registerAlias("genreDTO", GenreDTO.class);
        
        
        

       

        seb.setConfiguration(configuration);

        return seb.getObject();


    }
}
