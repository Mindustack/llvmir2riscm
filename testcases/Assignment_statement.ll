; ModuleID = 'Assignment_statement.c'
source_filename = "Assignment_statement.c"
target datalayout = "E-m:m-p:32:32-i8:8:32-i16:16:32-i64:64-n32-S64"
target triple = "mips"

@a = dso_local local_unnamed_addr global i32 0, align 4
@b = dso_local local_unnamed_addr global i32 0, align 4

; Function Attrs: noinline nounwind optnone
define dso_local i32 @main() local_unnamed_addr #0 {
  %1 = alloca i32, align 4
  store i32 0, ptr %1, align 4
  store i32 2, ptr @a, align 4, !tbaa !3
  store i32 3, ptr @a, align 4, !tbaa !3
  %2 = load i32, ptr @a, align 4, !tbaa !3
  store i32 %2, ptr @b, align 4, !tbaa !3
  ret i32 0
}

attributes #0 = { noinline nounwind optnone "frame-pointer"="all" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="mips32r2" "target-features"="+fpxx,+mips32r2,+nooddspreg,-noabicalls" }

!llvm.module.flags = !{!0, !1}
!llvm.ident = !{!2}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 7, !"frame-pointer", i32 2}
!2 = !{!"clang version 17.0.6"}
!3 = !{!4, !4, i64 0}
!4 = !{!"int", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
