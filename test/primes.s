# Code file created by Pascal2100 compiler 2015-09-04 13:36:36
        .extern write_char     
        .extern write_int      
        .extern write_string   
        .globl  _main          
        .globl  main           
_main:                                  
main:   call    prog$primes_1           # Start program
        movl    $0,%eax                 # Set status 0 and
        ret                             # terminate the program
proc$findprimes_2:
        enter   $40,$2                  # Start of findprimes
        movl    $2,%eax                 #   2
        movl    -8(%ebp),%edx           
        movl    %eax,-36(%edx)          # i1 :=
.L0003:                                 # Start while-statement
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   i1
        pushl   %eax                    
        movl    $1000,%eax              #   1000
        pushl   %eax                    
        movl    $2,%eax                 #   2
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setle   %al                     # Test <=
        cmpl    $0,%eax                 
        je      .L0004                  
        movl    $2,%eax                 #   2
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   i1
        movl    %eax,%ecx               
        popl    %eax                    
        imull   %ecx,%eax               #   *
        movl    -8(%ebp),%edx           
        movl    %eax,-40(%edx)          # i2 :=
.L0005:                                 # Start while-statement
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          #   i2
        pushl   %eax                    
        movl    $1000,%eax              #   1000
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setle   %al                     # Test <=
        cmpl    $0,%eax                 
        je      .L0006                  
        movl    $0,%eax                 #   enum value false (=0)
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          #   i2
        subl    $2,%eax                 
        movl    -4(%ebp),%edx           
        leal    -4028(%edx),%edx        
        popl    %ecx                    
        movl    %ecx,0(%edx,%eax,4)     # prime[x] :=
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          #   i2
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   i1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    -8(%ebp),%edx           
        movl    %eax,-40(%edx)          # i2 :=
        jmp     .L0005                  
.L0006:                                 # End while-statement
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   i1
        pushl   %eax                    
        movl    $1,%eax                 #   1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    -8(%ebp),%edx           
        movl    %eax,-36(%edx)          # i1 :=
        jmp     .L0003                  
.L0004:                                 # End while-statement
        leave                           # End of findprimes
        ret                             
func$ndigits_7:
        enter   $32,$2                  # Start of ndigits
                                        # Start if-statement
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   v
        pushl   %eax                    
        movl    $9,%eax                 #   9
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setle   %al                     # Test <=
        cmpl    $0,%eax                 
        je      .L0008                  
        movl    $1,%eax                 #   1
        movl    %eax,-32(%ebp)          # ndigits :=
        jmp     .L0009                  
.L0008:                                 
        movl    $1,%eax                 #   1
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   v
        pushl   %eax                    
        movl    $10,%eax                #   10
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    #   /
        pushl   %eax                    # Push param #1
        call    func$ndigits_7          
        addl    $4,%esp                 # Pop parameters
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    %eax,-32(%ebp)          # ndigits :=
.L0009:                                 # End if-statement
        movl    -32(%ebp),%eax          # Fetch return value
        leave                           # End of ndigits
        ret                             
proc$p4_10:
        enter   $36,$2                  # Start of p4
        movl    $4,%eax                 #   4
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1
        call    func$ndigits_7          
        addl    $4,%esp                 # Pop parameters
        movl    %eax,%ecx               
        popl    %eax                    
        subl    %ecx,%eax               #   -
        movl    -8(%ebp),%edx           
        movl    %eax,-36(%edx)          # nspaces :=
.L0011:                                 # Start while-statement
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   nspaces
        pushl   %eax                    
        movl    $0,%eax                 #   0
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setg    %al                     # Test >
        cmpl    $0,%eax                 
        je      .L0012                  
        movl    $32,%eax                #   char 32
        pushl   %eax                    # Push param #1.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   nspaces
        pushl   %eax                    
        movl    $1,%eax                 #   1
        movl    %eax,%ecx               
        popl    %eax                    
        subl    %ecx,%eax               #   -
        movl    -8(%ebp),%edx           
        movl    %eax,-36(%edx)          # nspaces :=
        jmp     .L0011                  
.L0012:                                 # End while-statement
        movl    -8(%ebp),%edx           
        movl    8(%edx),%eax            #   x
        pushl   %eax                    # Push param #1.
        call    write_int               
        addl    $4,%esp                 # Pop parameter.
        leave                           # End of p4
        ret                             
proc$printprimes_13:
        enter   $40,$2                  # Start of printprimes
        movl    $2,%eax                 #   2
        movl    -8(%ebp),%edx           
        movl    %eax,-36(%edx)          # i :=
        movl    $0,%eax                 #   0
        movl    -8(%ebp),%edx           
        movl    %eax,-40(%edx)          # nprinted :=
.L0014:                                 # Start while-statement
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   i
        pushl   %eax                    
        movl    $1000,%eax              #   1000
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setle   %al                     # Test <=
        cmpl    $0,%eax                 
        je      .L0015                  
                                        # Start if-statement
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   i
        subl    $2,%eax                 
        movl    -4(%ebp),%edx           
        leal    -4028(%edx),%edx        
        movl    0(%edx,%eax,4),%eax     #   prime[...]
        cmpl    $0,%eax                 
        je      .L0016                  
                                        # Start if-statement
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          #   nprinted
        pushl   %eax                    
        movl    $0,%eax                 #   0
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setg    %al                     # Test >
        pushl   %eax                    
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          #   nprinted
        pushl   %eax                    
        movl    $10,%eax                #   10
        movl    %eax,%ecx               
        popl    %eax                    
        cdq                             
        idivl   %ecx                    
        movl    %edx,%eax               #   mod
        pushl   %eax                    
        movl    $0,%eax                 #   0
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        sete    %al                     # Test =
        movl    %eax,%ecx               
        popl    %eax                    
        andl    %ecx,%eax               #   and
        cmpl    $0,%eax                 
        je      .L0017                  
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #1.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
.L0017:                                 # End if-statement
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   i
        pushl   %eax                    # Push param #1.
        call    proc$p4_10              
        addl    $4,%esp                 # Pop parameters.
        movl    -8(%ebp),%edx           
        movl    -40(%edx),%eax          #   nprinted
        pushl   %eax                    
        movl    $1,%eax                 #   1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    -8(%ebp),%edx           
        movl    %eax,-40(%edx)          # nprinted :=
.L0016:                                 # End if-statement
        movl    -8(%ebp),%edx           
        movl    -36(%edx),%eax          #   i
        pushl   %eax                    
        movl    $1,%eax                 #   1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    -8(%ebp),%edx           
        movl    %eax,-36(%edx)          # i :=
        jmp     .L0014                  
.L0015:                                 # End while-statement
        movl    $10,%eax                #   char 10
        pushl   %eax                    # Push param #1.
        call    write_char              
        addl    $4,%esp                 # Pop parameter.
        leave                           # End of printprimes
        ret                             
prog$primes_1:
        enter   $4032,$1                # Start of primes
        movl    $2,%eax                 #   2
        movl    -4(%ebp),%edx           
        movl    %eax,-4032(%edx)        # i :=
.L0018:                                 # Start while-statement
        movl    -4(%ebp),%edx           
        movl    -4032(%edx),%eax        #   i
        pushl   %eax                    
        movl    $1000,%eax              #   1000
        popl    %ecx                    
        cmpl    %eax,%ecx               
        movl    $0,%eax                 
        setle   %al                     # Test <=
        cmpl    $0,%eax                 
        je      .L0019                  
        movl    $1,%eax                 #   enum value true (=1)
        pushl   %eax                    
        movl    -4(%ebp),%edx           
        movl    -4032(%edx),%eax        #   i
        subl    $2,%eax                 
        movl    -4(%ebp),%edx           
        leal    -4028(%edx),%edx        
        popl    %ecx                    
        movl    %ecx,0(%edx,%eax,4)     # prime[x] :=
        movl    -4(%ebp),%edx           
        movl    -4032(%edx),%eax        #   i
        pushl   %eax                    
        movl    $1,%eax                 #   1
        movl    %eax,%ecx               
        popl    %eax                    
        addl    %ecx,%eax               #   +
        movl    -4(%ebp),%edx           
        movl    %eax,-4032(%edx)        # i :=
        jmp     .L0018                  
.L0019:                                 # End while-statement
        call    proc$findprimes_2       
        call    proc$printprimes_13     
        leave                           # End of primes
        ret                             
