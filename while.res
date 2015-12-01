# Code file created by Pascal2100 compiler 2015-12-01 14:39:06
        .extern write_char                         
        .extern write_int                         
        .extern write_string                         
        .globl  _main                   
        .globl  main                    
_main:                                  
main:   call    prog$whi_1              # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
proc$proc2_3:                                
        enter   $32, $3                 # Start of proc2
        movl    $5,%eax                 # 5
        movl    -4(%ebp),%edx           
        movl    %eax,-40(%edx)          # j :=
        movl    -12(%ebp),%edx          
        movl    8(%edx),%eax            #   f
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   h
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               # +
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          # i :=
        leave                           
        ret                             
proc$proc1_2:                                
        enter   $32, $2                 # Start of proc1
        movl    $5,%eax                 # 5
        movl    -4(%ebp),%edx           
        movl    %eax,-40(%edx)          # j :=
        movl    -4(%ebp),%edx           
        movl    -40(%edx),%eax          # j
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   h
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               # +
        movl    -4(%ebp),%edx           
        movl    %eax,-36(%edx)          # i :=
        leave                           
        ret                             
prog$whi_1:                                
        enter   $40, $1                 # Start of whi
        leave                           
        ret                             
