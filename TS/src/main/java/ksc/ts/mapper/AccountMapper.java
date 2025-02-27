package ksc.ts.mapper;


import ksc.ts.dto.account.CreateAccountRequest;
import ksc.ts.dto.account.CreateAccountResponse;
import ksc.ts.dto.account.GetAccountResponse;
import ksc.ts.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);


    // ReqeustDto -> Entity
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "accountBalance", expression = "java(java.math.BigDecimal.ZERO)")
    Account toEntity(CreateAccountRequest createAccountRequest);

    @Mapping(source = "user.id", target = "userId")
    CreateAccountResponse toCreateAccountResponse(Account account);

    @Mapping(source = "user.id", target = "userId")
    GetAccountResponse toGetAccountResponse(Account account);

}


// 필드명이 동일하면 자동 매핑된다.