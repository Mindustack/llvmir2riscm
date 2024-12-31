; ModuleID = 'k2.c'
source_filename = "k2.c"
target datalayout = "e-m:e-p270:32:32-p271:32:32-p272:64:64-i64:64-i128:128-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@x = dso_local local_unnamed_addr global i32 0, align 4
@_ZL1y = internal unnamed_addr global i32 0, align 4

; Function Attrs: mustprogress noinline nounwind optnone sspstrong uwtable
define dso_local void @_Z3foov() local_unnamed_addr #0 {
  %1 = load i32, ptr @x, align 4, !tbaa !5
  %2 = load i32, ptr @x, align 4, !tbaa !5
  %3 = mul nsw i32 %1, %2
  store i32 %3, ptr @_ZL1y, align 4, !tbaa !5
  %4 = load i32, ptr @x, align 4, !tbaa !5
  %5 = load i32, ptr @_ZL1y, align 4, !tbaa !5
  %6 = add nsw i32 %4, %5
  store i32 %6, ptr @x, align 4, !tbaa !5
  ret void
}

attributes #0 = { mustprogress noinline nounwind optnone sspstrong uwtable "min-legal-vector-width"="0" "no-trapping-math"="true" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+cmov,+cx8,+fxsr,+mmx,+sse,+sse2,+x87" "tune-cpu"="generic" }

!llvm.module.flags = !{!0, !1, !2, !3}
!llvm.ident = !{!4}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{i32 8, !"PIC Level", i32 2}
!2 = !{i32 7, !"PIE Level", i32 2}
!3 = !{i32 7, !"uwtable", i32 2}
!4 = !{!"clang version 18.1.8"}
!5 = !{!6, !6, i64 0}
!6 = !{!"int", !7, i64 0}
!7 = !{!"omnipotent char", !8, i64 0}
!8 = !{!"Simple C++ TBAA"}
